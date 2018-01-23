package com.oliva.antonio.dornatest.ui.common

/**
 * Created by antonio
 */

enum class ViewState {
    /**
     * Indicates that view is no loading elements and may be more elements to load
     */
    Idle,
    /**
     * Indicates that view is being updated with new elements
     */
    Refreshing
}
