package com.example.listener;

import org.testng.*;

import java.util.List;

public class GoldFileTestListener extends TestListenerAdapter implements IMethodInterceptor {

    /**
     * This is the entry point for the entire suite
     * @param tc is the test suite context
     */
    @Override
    public void onStart(ITestContext tc) {
        super.onStart(tc);
    }

    /**
     * This is the entry point for an individual test method running
     * @param tr the test method context
     */
    @Override
    public void onTestStart(ITestResult tr) {
        super.onTestStart(tr);
    }

    @Override
    public void onTestSuccess(ITestResult tr) {

    }

    @Override
    public void onTestFailure(ITestResult tr) {

    }

    @Override
    public void onTestSkipped(ITestResult tr) {

    }

    /**
     * This is the entry point for when the entire suite finishes
     */
    @Override
    public void onFinish(ITestContext tc) {

    }

    /**
     * @param list is the current list of test methods
     * @param iTestContext is the context of the entire suite
     * @return a list of tests that should be ran based on filters
     */
    public List<IMethodInstance> intercept(List<IMethodInstance> list, ITestContext iTestContext) {

            for (IMethodInstance methodInstance : list) {

            }

        return list;
    }


}
