package com.sports.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sports.common.resource.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface IViewState {
    val loading: Boolean
    val showErrorModal: Boolean
}

abstract class BaseViewModel<State : IViewState> : ViewModel() {
    private val initialState: State by lazy { createInitialState() }

    private val _event = Channel<BaseEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    abstract fun createInitialState(): State

    abstract fun showLoading(isLoading: Boolean)

    protected val currentState: State
        get() = uiState.value

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState

    protected fun setState(reduce: State.() -> State) {
        _uiState.update { currentState.reduce() }
    }

    protected suspend fun sendEvent(event: BaseEvent) {
        _event.send(event)
    }

    protected fun sendEventInViewModelScope(event: BaseEvent) {
        viewModelScope.launch { sendEvent(event) }
    }

    protected fun <P, T : Any> executeUseCase(
        useCase: UseCase<P, Flow<Resource<T>>>,
        parameter: P,
        onSuccess: (T) -> Unit,
        onError: (Throwable?) -> Unit,
        onLoading: () -> Unit = {},
        isShowLoading: Boolean = true,
    ) {
        viewModelScope.launch {
            useCase(parameter)
                .onEach {
                    when (it) {
                        is Resource.Success -> {
                            showLoading(false)
                            onSuccess(it.data)
                        }
                        is Resource.Error -> {
                            // Timber.e(it.exception)
                            showLoading(false)
                            it.exception?.printStackTrace()
                            onError(it.exception)
                        }
                        is Resource.Loading -> {
                            showLoading(isShowLoading)
                            onLoading()
                        }
                    }
                }
                .launchIn(viewModelScope)
        }
    }
}
