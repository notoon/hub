/**
 * Copyright (c) 2015-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.hub.domain.services.text;

import org.seedstack.business.Service;
import org.seedstack.hub.domain.model.document.DocumentId;
import org.seedstack.hub.domain.model.document.TextDocument;

import java.util.Set;

@Service
public interface TextFormatService {

    String renderHtml(TextDocument textDocument);

    Set<DocumentId> findRelativeReferences(TextDocument textDocument);

}
