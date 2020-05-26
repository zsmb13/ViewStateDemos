package co.zsmb.viewstatesdemo

import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map

inline val <T> T.exhaustive get() = this

inline fun <T, A> LiveData<T>.select(
    crossinline property: T.() -> A
) = map(property).distinctUntilChanged()

fun View.setVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}
