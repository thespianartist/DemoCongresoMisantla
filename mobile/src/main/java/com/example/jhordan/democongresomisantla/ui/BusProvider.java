package com.example.jhordan.democongresomisantla.ui;

import com.squareup.otto.Bus;

/**
 * Created by Jhordan on 09/11/14.
 */

    public final class BusProvider {
        private static final Bus BUS = new Bus();

        public static Bus getInstance() {
            return BUS;
        }

        private BusProvider() {
            // No instances.
        }
    }

