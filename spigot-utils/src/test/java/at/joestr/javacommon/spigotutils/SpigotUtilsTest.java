//
// Copyright (c) 2022 Joel Strasser <strasser999@gmail.com>
//
// Licensed under the EUPL-1.2 license.
//
// For the full license text consult the 'LICENSE' file from the repository.
//
package at.joestr.javacommon.spigotutils;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author joestr
 */
public class SpigotUtilsTest {

  @Test
  public void parsingFourSimpleArgument() {
    List<String> parsedResult = SpigotUtils.parseSingelQuotedArguments(new String[]{"this", "should", "work", "flawlessly"});
    Assertions.assertEquals(4, parsedResult.size());
  }

  @Test
  public void parsingFourArgumentsLastTwoBeingOne() {
    List<String> parsedResult = SpigotUtils.parseSingelQuotedArguments(new String[]{"this", "should", "'work", "flawlessly'"});
    Assertions.assertEquals(3, parsedResult.size());
  }

  @Test
  public void parsingFourArgumentsLastThreeBeingOne() {
    List<String> parsedResult = SpigotUtils.parseSingelQuotedArguments(new String[]{"this", "'should", "work", "flawlessly'"});
    Assertions.assertEquals(2, parsedResult.size());
  }

  @Test
  public void parsingFourArgumentsTwoInTheMiddleBeingOne() {
    List<String> parsedResult = SpigotUtils.parseSingelQuotedArguments(new String[]{"this", "'should", "work'", "flawlessly"});
    Assertions.assertEquals(3, parsedResult.size());
  }

  @Test
  public void parsingFourArgumentsFirstTwoBeingOne() {
    List<String> parsedResult = SpigotUtils.parseSingelQuotedArguments(new String[]{"'this", "should'", "work", "flawlessly"});
    Assertions.assertEquals(3, parsedResult.size());
  }

  @Test
  public void parsingFourArgumentsAllFourBeingOne() {
    List<String> parsedResult = SpigotUtils.parseSingelQuotedArguments(new String[]{"'this", "should", "work", "flawlessly'"});
    Assertions.assertEquals(1, parsedResult.size());
  }

  @Test
  public void parsingFourArgumentsAllFourBeingOneButExtraSingleQuoteInTheMiddle() {
    List<String> parsedResult = SpigotUtils.parseSingelQuotedArguments(new String[]{"'this", "sho'uld", "work", "flawlessly'"});
    Assertions.assertEquals(4, parsedResult.size());
  }

  @Test
  public void parsingFourArgumentsAllFourBeingOneButExtraSingleQuoteAtStart() {
    List<String> parsedResult = SpigotUtils.parseSingelQuotedArguments(new String[]{"'this", "'should", "work", "flawlessly'"});
    Assertions.assertEquals(4, parsedResult.size());
  }

  @Test
  public void parsingFourArgumentsAllFourBeingOneButExtraSingleQuoteAtTheEnd() {
    List<String> parsedResult = SpigotUtils.parseSingelQuotedArguments(new String[]{"'this", "should'", "work", "flawlessly'"});
    Assertions.assertEquals(4, parsedResult.size());
  }

  @Test
  public void parsingFourArgumentsAllFourBeingOneButExtraSingleQuoteAtTheBeginningAndAtTheEnd() {
    List<String> parsedResult = SpigotUtils.parseSingelQuotedArguments(new String[]{"'this", "'should'", "work", "flawlessly'"});
    Assertions.assertEquals(4, parsedResult.size());
  }

  @Test
  public void parsingFourArgumentsAllFourBeingOneButExtraSingleQuoteAtTheBeginningAndAtTheEndTwice() {
    List<String> parsedResult = SpigotUtils.parseSingelQuotedArguments(new String[]{"'this", "'should'", "'work'", "flawlessly'"});
    Assertions.assertEquals(4, parsedResult.size());
  }

  @Test
  public void parsingFourArgumentsAllFourBeingOneButExtraSingleQuoteAtTheBeginningAndAtTheEndTwiceAndExtraInTheMiddle() {
    List<String> parsedResult = SpigotUtils.parseSingelQuotedArguments(new String[]{"'this", "'sho'uld'", "'wo'rk'", "flawlessly'"});
    Assertions.assertEquals(4, parsedResult.size());
  }

  @Test
  public void parsingFourArgumentsAllFourBeingOneWithDoubleSingleQuotes() {
    List<String> parsedResult = SpigotUtils.parseSingelQuotedArguments(new String[]{"''this", "should", "work", "flawlessly''"});
    Assertions.assertEquals(4, parsedResult.size());
  }

  @Test
  public void parsingFourArgumentsAllFourBeingOneWithEspacedSingleQuotes() {
    List<String> parsedResult = SpigotUtils.parseSingelQuotedArguments(new String[]{"'\\'this", "\\'shoul\\'d", "work", "flawlessly\\''"});
    Assertions.assertEquals(1, parsedResult.size());
  }
}
