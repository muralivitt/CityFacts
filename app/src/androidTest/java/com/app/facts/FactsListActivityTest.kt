package com.app.facts


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.app.facts.adapters.CityListAdapter
import com.app.facts.view.FactsListActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class FactsListActivityTest {

    @get:Rule
    val mActivityRule: ActivityTestRule<FactsListActivity> = ActivityTestRule(FactsListActivity::class.java, true, false)

    @Test
    fun scrollToItemAndClick() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.rvCityFactsList)).perform(
                RecyclerViewActions.actionOnItemAtPosition<CityListAdapter.MyViewHolder>(0, click()))

//        mActivityRule.launchActivity(Intent())
//        intended(hasComponent(DetailsActivity::class.java!!.name))
    }
}
