package com.genesis.practicaltest


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    //check visibility of progressbar is gone
    @Test
    fun testProgressBarIsGone() {
        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    //check that the android:text is correct
    @Test
    fun testButtonIdText() {
        onView(withId(R.id.btnRetry)).check(matches(withText("RETRY")))

    }

    //check is recycler view is displayed
    @Test
    fun testRecyclerViewIsDisplayed() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    //Test passes when error occurs e.g no internet connection
    @Test
    fun testRetryButtonClickedWhenErrorOccurs() {
        onView(withId(R.id.btnRetry)).perform(ViewActions.click())
    }

    //Test passes when error occurs e.g no internet connection
    @Test
    fun testErrorTextVisibilityIsVisible() {
        onView(withId(R.id.errorTextView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }


    //Test passes when no error occurs
    @Test
    fun testRetryButtonClickedWhenNoError() {
        onView(withId(R.id.btnRetry)).perform()
    }

    //Test passes when no error occurs
    @Test
    fun testErrorTextVisibilityIsGone() {
        onView(withId(R.id.errorTextView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }


}