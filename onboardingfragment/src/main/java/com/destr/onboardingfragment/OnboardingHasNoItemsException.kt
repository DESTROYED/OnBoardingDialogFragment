package com.destr.onboardingfragment

import java.lang.Exception

class OnboardingHasNoItemsException :
    Exception("Please make sure, that some items was added to OnboardingFragment")