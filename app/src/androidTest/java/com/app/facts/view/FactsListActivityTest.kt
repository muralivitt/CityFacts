package com.app.facts.view


import android.support.test.InstrumentationRegistry
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.Toolbar
import android.test.suitebuilder.annotation.LargeTest

import com.app.facts.R
import com.app.facts.adapters.CityListAdapter

import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.`is`

@LargeTest
@RunWith(AndroidJUnit4::class)
class FactsListActivityTest {

    @Rule
    var mFactsListActivityTestRule: ActivityTestRule<FactsListActivity> = ActivityTestRule(FactsListActivity::class.java, true, true)


    @Test
    fun factsListActivityTest() {

        //Case 1 : Test toolbar title is loading
        toolbarTitleTest()

        //Case 2 : Test toolbar title while loading data
        toolbarSubTitleTest_Loading()


        //Wait for UI to render all the items in list
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        //Case 3 : Test toolbar title is on success response
        toolbarSubTitleTest_OnSuccess()


        //Case 4 : Click on 1st item in the list
        onView(withId(R.id.rvCityFactsList))
                .perform(RecyclerViewActions.actionOnItemAtPosition<CityListAdapter.MyViewHolder>(0, click()))

        //Case 5 : Check Details activity is launched
        onView(withId(R.id.tvTitle)).check(matches(withText("Beavers")))
    }

    @Test
    private fun toolbarTitleTest() {
        val title = InstrumentationRegistry.getTargetContext()
                .getString(R.string.app_name)
        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(withToolbarText(`is`(title), "title"))).check(matches(isDisplayed()))
    }

    @Test
    private fun toolbarSubTitleTest_Loading() {
        val loading = InstrumentationRegistry.getTargetContext()
                .getString(R.string.loading)
        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(withToolbarText(`is`(loading), "subtitle"))).check(matches(isDisplayed()))
    }

    @Test
    private fun toolbarSubTitleTest_NoNetwork() {
        val networkError = InstrumentationRegistry.getTargetContext()
                .getString(R.string.network_error)
        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(withToolbarText(`is`(networkError), "subtitle"))).check(matches(isDisplayed()))
    }

    @Test
    private fun toolbarSubTitleTest_ResponseError() {
        val responseError = InstrumentationRegistry.getTargetContext()
                .getString(R.string.response_error)
        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(withToolbarText(`is`(responseError), "subtitle"))).check(matches(isDisplayed()))
    }

    @Test
    private fun toolbarSubTitleTest_OnSuccess() {
        val response = InstrumentationRegistry.getTargetContext()
                .getString(R.string.response)
        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(withToolbarText(`is`(response), "subtitle"))).check(matches(isDisplayed()))
    }

    private fun withToolbarText(
            textMatcher: Matcher<CharSequence>, titleType: String): Matcher<Any> {
        return object : BoundedMatcher<Any, Toolbar>(Toolbar::class.java) {
            override fun describeTo(description: org.hamcrest.Description) {
                description.appendText("with toolbar title: ")
                textMatcher.describeTo(description)
            }

            public override fun matchesSafely(toolbar: Toolbar): Boolean {
                return if (titleType == "title") { // title
                    textMatcher.matches(toolbar.title)
                } else { // Subtitle
                    textMatcher.matches(toolbar.subtitle)
                }
            }
        }
    }
}
