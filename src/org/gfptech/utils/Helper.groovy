package org.gfptech.utils

class Helper {
    static String formatMessage(String project, String status) {
        return "${project} - Build status: ${status}"
    }
}