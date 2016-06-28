/**
 * Copyright (c) 2015-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.it;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.hub.application.fetch.Manifest;
import org.seedstack.hub.application.fetch.ManifestParser;
import org.seedstack.seed.it.SeedITRunner;

import javax.inject.Inject;
import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SeedITRunner.class)
public class ManifestParserIT {

    @Inject
    private ManifestParser manifestParser;

    @Test
    public void parse_manifest() throws Exception {
        File directory = new File("src/test/resources/components/component1");
        Manifest manifest = manifestParser.parseManifest(directory);
        assertThat(manifest).isNotNull();
        assertThat(manifest.getId()).isEqualTo("component1");
        assertThat(manifest.getName()).isEqualTo("Component 1");
        assertThat(manifest.getReleases()).hasSize(2);
        assertThat(manifest.getReleases().get(0).getVersion()).isEqualTo("1.0.0");
        assertThat(manifest.getReleases().get(0).getUrl()).isEqualTo("https://github.com/seedstack/mongodb-addon/releases/tag/v1.0.0");
        assertThat(manifest.getReleases().get(0).getDate()).isEqualTo("2015-11-17");

        assertThat(manifest.getOwner()).isEqualTo("user2@email.com");
        assertThat(manifest.getUrl()).isEqualTo("https://github.com/seedstack/mongodb-addon");
        assertThat(manifest.getIssues()).isEqualTo("https://github.com/seedstack/mongodb-addon/issues");
        assertThat(manifest.getSummary()).isEqualTo("This is the awesome component 1.");
        assertThat(manifest.getLicense()).isEqualTo("MPL2");
        assertThat(manifest.getIcon()).isEqualTo("images/icon.png");
        assertThat(manifest.getImages()).containsOnly("images/screenshot-1.png", "images/screenshot-2.png", "images/screenshot-3.png");
        assertThat(manifest.getMaintainers()).containsOnly("pierre.thirouin@ext.mpsa.com", "kavi.ramyead@ext.mpsa.com");
    }
}
