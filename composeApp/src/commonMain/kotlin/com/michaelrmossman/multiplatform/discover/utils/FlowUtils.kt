package com.michaelrmossman.multiplatform.discover.utils

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.Flow

object FlowUtils {

    // https://stackoverflow.com/questions/65356805/kotlin-flow-why-the-function-combine-can-only-take-maximum-5-flows-in-paramet
    @NativeCoroutines
    inline fun <T1, T2, T3, T4, T5, T6, R> combine(
        flow: Flow<T1>,
        flow2: Flow<T2>,
        flow3: Flow<T3>,
        flow4: Flow<T4>,
        flow5: Flow<T5>,
        flow6: Flow<T6>,
        crossinline transform: suspend (T1, T2, T3, T4, T5, T6) -> R
    ) : Flow<R> {
        return combine(
            flow, flow2, flow3, flow4, flow5, flow6
        ) { args: Array<*> ->
            @Suppress("UNCHECKED_CAST")
            transform(
                args[0] as T1,
                args[1] as T2,
                args[2] as T3,
                args[3] as T4,
                args[4] as T5,
                args[5] as T6,
            )
        }
    }
}