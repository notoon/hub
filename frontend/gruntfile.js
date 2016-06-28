/*
 * Copyright (c) 2015-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
/* global module: false, grunt: false */

module.exports = function (grunt) {
    'use strict';

    grunt.initConfig({
        w20: {
            optimize: {
                options: {
                    basePath: 'work',
                    buildConfig: {
                        out: 'work/hub.min.js'
                    }
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-w20');
};
