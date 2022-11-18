//
// Copyright (c) 2022 Joel Strasser <strasser999@gmail.com>
//
// Licensed under the EUPL-1.2 license.
//
// For the full license text consult the 'LICENSE' file from the repository.
//
package at.joestr.javacommon.spigotutils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joestr
 */
public class SpigotUtils {

  /**
   * Parses given arguments and transforms them into a List of arguments
   * respecting single-quoted argument.
   *
   * @param arguments The array of arguments
   * @return A List of parsed arguments
   */
  public static List<String> parseSingelQuotedArguments(String[] arguments) {
    List<String> result = new ArrayList<>();

    boolean isParsingQuotedArguemnt = false;

    for (String arg : arguments) {
      if (!isParsingQuotedArguemnt && arg.startsWith("'") && arg.endsWith("'")) {
        result.add(arg.substring(1, arg.length() - 1));
      } else if (!isParsingQuotedArguemnt && arg.startsWith("'")) {
        result.add(arg.substring(1, arg.length()));
        isParsingQuotedArguemnt = true;
      } else if (isParsingQuotedArguemnt) {
        String finalString;
        if (arg.endsWith("'") && !"\\".equals(arg.charAt(arg.length() - 2))) {
          finalString = arg.substring(0, arg.length() - 1);
          isParsingQuotedArguemnt = false;
        } else {
          finalString = arg;
        }
        result.set(result.size() - 1, result.get(result.size() - 1) + " " + finalString);
      } else {
        result.add(arg);
      }
    }

    for (String arg : result) {
      if (arg.contains("'")) {
        int i = arg.length() - 1;
        while (i > 0) {
          int lastIdx = arg.lastIndexOf("'", i);
          if (lastIdx == -1) {
            break;
          } else if (lastIdx > 0 && "\\".equals(String.valueOf(arg.charAt(lastIdx - 1)))) {
            arg = arg.substring(0, lastIdx - 1) + arg.substring(lastIdx, arg.length());
            i = lastIdx - 2;
          } else {
            // Invalid parsing result. So return original arguments.
            return List.of(arguments);
          }
        }
      }
    }

    return result;
  }
}
