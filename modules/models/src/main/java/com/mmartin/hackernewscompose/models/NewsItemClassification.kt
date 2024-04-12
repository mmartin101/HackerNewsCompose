package com.mmartin.hackernewscompose.models

enum class NewsItemClassification {
    STORY, JOB, COMMENT, POLL, POLLOPT, UNKNOWN;


    companion object {

        /**
         * Safer version of valueOf()
         *
         * @param type HN Item.type, or NewsItem.type in our case
         * @return enum of type, otherwise default to UNKNOWN
         */
        fun parse(type: String?): NewsItemClassification {
            for (it in values()) {
                if (it.toString().equals(type, ignoreCase = true)) {
                    return it
                }
            }

            return UNKNOWN
        }
    }
}
