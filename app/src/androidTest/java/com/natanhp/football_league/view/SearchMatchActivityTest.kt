package com.natanhp.football_league.view

import android.view.KeyEvent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.natanhp.football_league.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class SearchMatchActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(SearchMatchActivity::class.java)

    @Test
    fun testSearchBehaviour() {
        onView(withId(R.id.search_bar))
            .check(matches(isDisplayed()))
        onView(withId(R.id.search_bar)).perform(typeText("England"))
        onView(withId(R.id.search_bar)).perform(pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
        sleep(5000)
        onView(withId(R.id.search_bar))
            .perform(pressKey(KeyEvent.KEYCODE_DEL))
            .perform(pressKey(KeyEvent.KEYCODE_DEL))
            .perform(pressKey(KeyEvent.KEYCODE_DEL))
            .perform(pressKey(KeyEvent.KEYCODE_DEL))
            .perform(pressKey(KeyEvent.KEYCODE_DEL))
            .perform(pressKey(KeyEvent.KEYCODE_DEL))
            .perform(pressKey(KeyEvent.KEYCODE_DEL))
            .perform(typeText("Italy"))
        onView(withId(R.id.search_bar)).perform(pressKey(KeyEvent.KEYCODE_ENTER))
        sleep(1000)
    }
}