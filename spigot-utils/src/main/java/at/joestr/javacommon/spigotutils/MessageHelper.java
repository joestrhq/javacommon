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
import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Function;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.command.CommandSender;

/**
 * @author Joel
 */
public class MessageHelper {

  final private BiFunction<String, Locale, String> languageResolverFunction;
  private String path;
  private Locale locale;
  private String prefixPath;
  private boolean showPrefix;
  final private List<Function<String, String>> modifiers;
  private CommandSender receiver;

  public MessageHelper(BiFunction<String, Locale, String> languageResolverFunction) {
    this.languageResolverFunction = languageResolverFunction;
    this.modifiers = new ArrayList<>();
  }

  public MessageHelper path(String path) {
    this.path = path;
    return this;
  }

  public MessageHelper locale(Locale locale) {
    this.locale = locale;
    return this;
  }

  public MessageHelper prefixPath(String prefixPath) {
    this.prefixPath = prefixPath;
    return this;
  }

  public MessageHelper showPrefix(boolean showPrefix) {
    this.showPrefix = showPrefix;
    return this;
  }

  public MessageHelper modify(Function<String, String> modify) {
    this.modifiers.add(modify);
    return this;
  }

  public MessageHelper receiver(CommandSender receiver) {
    this.receiver = receiver;
    return this;
  }

  /**
   * Sends the receiver a message.
   */
  public void send() {
    receiver.spigot().sendMessage(ComponentSerializer.parse(this.build(false)));
  }

  /**
   * Gets the string. It may be modified.
   *
   * @return String The string which can be modified.
   */
  public String string() {
    return this.build(false);
  }

  /**
   * Gets the string in an unmodified state.
   *
   * @return String the unmodified string
   */
  public String rawString() {
    return this.build(true);
  }

  private String build(boolean raw) {
    String message
      = languageResolverFunction.apply(this.path, this.locale);

    if (!raw) {
      if (showPrefix) {
        message
          = message.replace(
            "%prefix",
            new MessageHelper(languageResolverFunction).locale(this.locale).path(prefixPath).string());
      } else {
        message = message.replace("%prefix", "");
      }

      for (Function<String, String> modifier : this.modifiers) {
        modifier.apply(message);
      }
    }

    return message;
  }
}
