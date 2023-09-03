//
// Copyright (c) 2022 Joel Strasser <strasser999@gmail.com>
//
// Licensed under the EUPL-1.2 license.
//
// For the full license text consult the 'LICENSE' file from the repository.
//
package at.joestr.javacommon.foliautils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FoliaUtilsTest {

  @Test
  public void isFoliaShouldReturnTrue() {
    boolean result = FoliaUtils.isFolia();
    Assertions.assertEquals(true, result);
  }
}
