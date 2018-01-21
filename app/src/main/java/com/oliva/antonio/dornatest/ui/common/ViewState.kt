package com.oliva.antonio.brastlewarkguide.ui.common

/**
 * Created by antoniojoseolivafaura on 04/12/2017.
 */

enum class ViewState {
    /**
     * Indicates that view is no loading elements and may be more elements to load
     */
    Idle,
    /**
     * Indicates that view is being updated with new elements
     */
    Refreshing,
    /**
     * Indicates taht view is loading more elements from its current data set
     */
    LoadingMore,
    /**
     * Indicates that there is no more elements to load from data set
     */
    AllLoaded }
