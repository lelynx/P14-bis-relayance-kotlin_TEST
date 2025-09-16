package com.kirabium.relayance

import com.kirabium.relayance.ui.util.EmailUtils
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test

class EmailUtilsTest {

    @Test
    fun isValidEmail_validEmails_returnTrue() {
        assertTrue(EmailUtils.isValidEmail("john.doe@example.com"))
        assertTrue(EmailUtils.isValidEmail("a@b.co"))
    }

    @Test
    fun isValidEmail_invalidEmails_returnFalse() {
        assertFalse(EmailUtils.isValidEmail("plainaddress"))
        assertFalse(EmailUtils.isValidEmail("@no-local-part.com"))
        assertFalse(EmailUtils.isValidEmail("no-at-symbol.com"))
    }
}