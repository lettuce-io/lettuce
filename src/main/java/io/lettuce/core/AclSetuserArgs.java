/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.lettuce.core;

import static io.lettuce.core.protocol.CommandKeyword.*;

import java.util.ArrayList;
import java.util.List;

import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.protocol.CommandType;
import io.lettuce.core.protocol.ProtocolKeyword;

/**
 * Argument list builder for the Redis <a href="https://redis.io/commands/acl-setuser">ACL SETUSER</a> command.
 * <p>
 * {@link AclSetuserArgs} is a mutable object and instances should be used only once to avoid shared mutable state.
 *
 * @author Mikhael Sokolov
 * @author Rohan Nagar
 * @since 6.1
 */
public class AclSetuserArgs implements CompositeArgument {

    private final List<SetuserArg> arguments = new ArrayList<>();

    /**
     * Builder entry points for {@link AclSetuserArgs}.
     */
    public static class Builder {

        /**
         * Utility constructor.
         */
        private Builder() {
        }

        /**
         * Creates new {@link AclSetuserArgs} and set user active.
         *
         * @return new {@link AclSetuserArgs} and set user active.
         * @see AclSetuserArgs#on()
         */
        public static AclSetuserArgs on() {
            return new AclSetuserArgs().on();
        }

        /**
         * Creates new {@link AclSetuserArgs} and set user inactive.
         *
         * @return new {@link AclSetuserArgs} and set user inactive.
         * @see AclSetuserArgs#off()
         */
        public static AclSetuserArgs off() {
            return new AclSetuserArgs().off();
        }

        /**
         * Creates new {@link AclSetuserArgs} and adds accessible key pattern.
         *
         * @param keyPattern accessible key pattern
         * @return new {@link AclSetuserArgs} and adds accessible key pattern.
         * @see AclSetuserArgs#keyPattern(String)
         */
        public static AclSetuserArgs keyPattern(String keyPattern) {
            return new AclSetuserArgs().keyPattern(keyPattern);
        }

        /**
         * Creates new {@link AclSetuserArgs} and allows the user to access all the keys.
         *
         * @return new {@link AclSetuserArgs} and allows the user to access all the keys.
         * @see AclSetuserArgs#allKeys()
         */
        public static AclSetuserArgs allKeys() {
            return new AclSetuserArgs().allKeys();
        }

        /**
         * Creates new {@link AclSetuserArgs} and removes all the key patterns from the list of key patterns the user can access.
         *
         * @return new {@link AclSetuserArgs} and removes all the key patterns from the list of key patterns the user can access.
         * @see AclSetuserArgs#resetKeys()
         */
        public static AclSetuserArgs resetKeys() {
            return new AclSetuserArgs().resetKeys();
        }

        /**
         * Creates new {@link AclSetuserArgs} and adds accessible channel pattern.
         *
         * @param channelPattern accessible channel pattern
         * @return new {@link AclSetuserArgs} and adds accessible channel pattern.
         * @see AclSetuserArgs#channelPattern(String)
         */
        public static AclSetuserArgs channelPattern(String channelPattern) {
            return new AclSetuserArgs().channelPattern(channelPattern);
        }

        /**
         * Creates new {@link AclSetuserArgs} and allows the user to access all the Pub/Sub channels.
         *
         * @return new {@link AclSetuserArgs} and allows the user to access all the Pub/Sub channels.
         * @see AclSetuserArgs#allChannels()
         */
        public static AclSetuserArgs allChannels() {
            return new AclSetuserArgs().allChannels();
        }

        /**
         * Creates new {@link AclSetuserArgs} and removes all channel patterns from the list of Pub/Sub channel patterns the user can access.
         *
         * @return new {@link AclSetuserArgs} and removes all channel patterns from the list of Pub/Sub channel patterns the user can access.
         * @see AclSetuserArgs#resetChannels()
         */
        public static AclSetuserArgs resetChannels() {
            return new AclSetuserArgs().resetChannels();
        }

        /**
         * Creates new {@link AclSetuserArgs} and adds this command to the list of the commands the user can call.
         *
         * @param command accessible command
         * @return new {@link AclSetuserArgs} and adds this command to the list of the commands the user can call.
         * @see AclSetuserArgs#addCommand(CommandType)
         */
        public static AclSetuserArgs addCommand(CommandType command) {
            return new AclSetuserArgs().addCommand(command);
        }

        /**
         * Creates new {@link AclSetuserArgs} and adds the specified command to the list of the commands the user can execute.
         *
         * @param command accessible command
         * @param subCommand accessible subcommand
         * @return new {@link AclSetuserArgs} and adds the specified command to the list of the commands the user can execute.
         * @see AclSetuserArgs#addCommand(CommandType, ProtocolKeyword)
         */
        public static AclSetuserArgs addCommand(CommandType command, ProtocolKeyword subCommand) {
            return new AclSetuserArgs().addCommand(command, subCommand);
        }

        /**
         * Creates new {@link AclSetuserArgs} and adds all the commands there are in the server.
         *
         * @return new {@link AclSetuserArgs} and adds all the commands there are in the server.
         * @see AclSetuserArgs#allCommands()
         */
        public static AclSetuserArgs allCommands() {
            return new AclSetuserArgs().allCommands();
        }

        /**
         * Creates new {@link AclSetuserArgs} and removes this command to the list of the commands the user can call.
         *
         * @param command inaccessible command
         * @return new {@link AclSetuserArgs} and removes this command to the list of the commands the user can call.
         * @see AclSetuserArgs#removeCommand(CommandType)
         */
        public static AclSetuserArgs removeCommand(CommandType command) {
            return new AclSetuserArgs().removeCommand(command);
        }

        /**
         * Creates new {@link AclSetuserArgs} and removes the specified command to the list of the commands the user can execute.
         *
         * @param command inaccessible command
         * @param subCommand inaccessible subcommand
         * @return new {@link AclSetuserArgs} and removes the specified command to the list of the commands the user can execute.
         * @see AclSetuserArgs#removeCommand(CommandType, ProtocolKeyword)
         */
        public static AclSetuserArgs removeCommand(CommandType command, ProtocolKeyword subCommand) {
            return new AclSetuserArgs().removeCommand(command, subCommand);
        }

        /**
         * Creates new {@link AclSetuserArgs} and removes all the commands the user can execute.
         *
         * @return new {@link AclSetuserArgs} and removes all the commands the user can execute.
         * @see AclSetuserArgs#noCommands()
         */
        public static AclSetuserArgs noCommands() {
            return new AclSetuserArgs().noCommands();
        }

        /**
         * Creates new {@link AclSetuserArgs} and adds all the commands in the specified category to the list of commands the user is able to execute.
         *
         * @param category specified category
         * @return new {@link AclSetuserArgs} and adds all the commands in the specified category to the list of commands the user is able to execute.
         * @see AclSetuserArgs#addCategory(AclCategory)
         */
        public static AclSetuserArgs addCategory(AclCategory category) {
            return new AclSetuserArgs().addCategory(category);
        }

        /**
         * Creates new {@link AclSetuserArgs} and removes all the commands in the specified category to the list of commands the user is able to execute.
         *
         * @param category specified category
         * @return new {@link AclSetuserArgs} and removes all the commands in the specified category to the list of commands the user is able to execute.
         * @see AclSetuserArgs#removeCategory(AclCategory)
         */
        public static AclSetuserArgs removeCategory(AclCategory category) {
            return new AclSetuserArgs().removeCategory(category);
        }

        /**
         * Creates new {@link AclSetuserArgs} and sets the user as a "no password".
         *
         * @return new {@link AclSetuserArgs} and sets the user as a "no password".
         * @see AclSetuserArgs#nopass()
         */
        public static AclSetuserArgs nopass() {
            return new AclSetuserArgs().nopass();
        }

        /**
         * Creates new {@link AclSetuserArgs} and sets the user as having no associated passwords.
         *
         * @return new {@link AclSetuserArgs} and sets the user as having no associated passwords.
         * @see AclSetuserArgs#resetpass()
         */
        public static AclSetuserArgs resetpass() {
            return new AclSetuserArgs().resetpass();
        }

        /**
         * Creates new {@link AclSetuserArgs} and adds the specified clear text password as an hashed password in the list of the users passwords.
         *
         * @param password clear text password
         * @return new {@link AclSetuserArgs} and adds the specified clear text password as an hashed password in the list of the users passwords.
         * @see AclSetuserArgs#addPassword(String)
         */
        public static AclSetuserArgs addPassword(String password) {
            return new AclSetuserArgs().addPassword(password);
        }

        /**
         * Creates new {@link AclSetuserArgs} and adds the specified hashed password to the list of user passwords.
         *
         * @param hashedPassword hashed password
         * @return new {@link AclSetuserArgs} and adds the specified hashed password to the list of user passwords.
         * @see AclSetuserArgs#addHashedPassword(String)
         */
        public static AclSetuserArgs addHashedPassword(String hashedPassword) {
            return new AclSetuserArgs().addHashedPassword(hashedPassword);

        }

        /**
         * Creates new {@link AclSetuserArgs} and removes the specified clear text password as an hashed password in the list of the users passwords.
         *
         * @param password clear text password
         * @return new {@link AclSetuserArgs} and removes the specified clear text password as an hashed password in the list of the users passwords.
         * @see AclSetuserArgs#removePassword(String)
         */
        public static AclSetuserArgs removePassword(String password) {
            return new AclSetuserArgs().removePassword(password);

        }

        /**
         * Creates new {@link AclSetuserArgs} and removes the specified hashed password to the list of user passwords.
         *
         * @param hashedPassword hashed password
         * @return new {@link AclSetuserArgs} and removes the specified hashed password to the list of user passwords.
         * @see AclSetuserArgs#removeHashedPassword(String)
         */
        public static AclSetuserArgs removeHashedPassword(String hashedPassword) {
            return new AclSetuserArgs().removeHashedPassword(hashedPassword);

        }

        /**
         * Creates new {@link AclSetuserArgs} and removes any capability from the user.
         *
         * @return new {@link AclSetuserArgs} and removes any capability from the user.
         * @see AclSetuserArgs#reset()
         */
        public static AclSetuserArgs reset() {
            return new AclSetuserArgs().reset();
        }
    }

    /**
     * Set user active.
     *
     * @return {@code this}
     */
    public AclSetuserArgs on() {
        this.arguments.add(new Active(true));
        return this;
    }

    /**
     * Set user inactive.
     *
     * @return {@code this}
     */
    public AclSetuserArgs off() {
        this.arguments.add(new Active(false));
        return this;
    }

    /**
     * Adds accessible key pattern.
     *
     * @param keyPattern accessible key pattern
     * @return {@code this}
     */
    public AclSetuserArgs keyPattern(String keyPattern) {
        this.arguments.add(new KeyPattern(keyPattern));
        return this;
    }

    /**
     * Allows the user to access all the keys.
     *
     * @return {@code this}
     */
    public AclSetuserArgs allKeys() {
        this.arguments.add(new AllKeys());
        return this;
    }

    /**
     * Removes all the key patterns from the list of key patterns the user can access.
     *
     * @return {@code this}
     */
    public AclSetuserArgs resetKeys() {
        this.arguments.add(new ResetKeys());
        return this;
    }

    /**
     * Adds accessible channel pattern.
     *
     * @param channelPattern accessible channel pattern
     * @return {@code this}
     */
    public AclSetuserArgs channelPattern(String channelPattern) {
        this.arguments.add(new ChannelPattern(channelPattern));
        return this;
    }

    /**
     * Allows the user to access all the Pub/Sub channels.
     *
     * @return {@code this}
     */
    public AclSetuserArgs allChannels() {
        this.arguments.add(new AllChannels());
        return this;
    }

    /**
     * Removes all channel patterns from the list of Pub/Sub channel patterns the user can access.
     *
     * @return {@code this}
     */
    public AclSetuserArgs resetChannels() {
        this.arguments.add(new ResetChannels());
        return this;
    }

    /**
     * Adds this command to the list of the commands the user can call.
     *
     * @param command accessible command
     * @return {@code this}
     */
    public AclSetuserArgs addCommand(CommandType command) {
        return addCommand(command, null);
    }

    /**
     * Adds all the commands there are in the server.
     *
     * @return {@code this}
     */
    public AclSetuserArgs addCommand(CommandType command, ProtocolKeyword subCommand) {
        this.arguments.add(new AddCommand(new CommandSubcommandPair(command, subCommand)));
        return this;
    }

    /**
     * Adds all the commands there are in the server.
     *
     * @return {@code this}
     */
    public AclSetuserArgs allCommands() {
        this.arguments.add(new AllCommands());
        return this;
    }

    /**
     * Removes this command to the list of the commands the user can call.
     *
     * @param command inaccessible command
     * @return {@code this}
     */
    public AclSetuserArgs removeCommand(CommandType command) {
        return removeCommand(command, null);
    }

    /**
     * Removes the specified command to the list of the commands the user can execute.
     *
     * @param command inaccessible command
     * @param subCommand inaccessible subcommand
     * @return {@code this}
     */
    public AclSetuserArgs removeCommand(CommandType command, ProtocolKeyword subCommand) {
        this.arguments.add(new RemoveCommand(new CommandSubcommandPair(command, subCommand)));
        return this;
    }

    /**
     * Removes all the commands the user can execute.
     *
     * @return {@code this}
     */
    public AclSetuserArgs noCommands() {
        this.arguments.add(new NoCommands());
        return this;
    }

    /**
     * Adds all the commands in the specified category to the list of commands the user is able to execute.
     *
     * @param category specified category
     * @return {@code this}
     */
    public AclSetuserArgs addCategory(AclCategory category) {
        this.arguments.add(new AddCategory(category));
        return this;
    }

    /**
     * Removes all the commands in the specified category to the list of commands the user is able to execute.
     *
     * @param category specified category
     * @return {@code this}
     */
    public AclSetuserArgs removeCategory(AclCategory category) {
        this.arguments.add(new RemoveCategory(category));
        return this;
    }

    /**
     * Sets the user as a "no password".
     *
     * @return {@code this}
     */
    public AclSetuserArgs nopass() {
        this.arguments.add(new NoPass());
        return this;
    }

    /**
     * Flushes the list of allowed passwords and removes the "no password" status. After resetting
     * the password there is no way to authenticate as the user without adding some password (or
     * setting it as {@link #nopass()} later).
     *
     * @return {@code this}
     */
    public AclSetuserArgs resetpass() {
        this.arguments.add(new ResetPass());
        return this;
    }

    /**
     * Adds the specified clear text password as an hashed password in the list of the users passwords.
     *
     * @param password clear text password
     * @return {@code this}
     */
    public AclSetuserArgs addPassword(String password) {
        this.arguments.add(new AddPassword(password));
        return this;
    }

    /**
     * Adds the specified hashed password to the list of user passwords.
     *
     * @param hashedPassword hashed password
     * @return {@code this}
     */
    public AclSetuserArgs addHashedPassword(String hashedPassword) {
        this.arguments.add(new AddHashedPassword(hashedPassword));
        return this;
    }

    /**
     * Removes the specified clear text password as an hashed password in the list of the users passwords.
     *
     * @param password clear text password
     * @return {@code this}
     */
    public AclSetuserArgs removePassword(String password) {
        this.arguments.add(new RemovePassword(password));
        return this;
    }

    /**
     * Removes the specified hashed password to the list of user passwords.
     *
     * @param hashedPassword hashed password
     * @return {@code this}
     */
    public AclSetuserArgs removeHashedPassword(String hashedPassword) {
        this.arguments.add(new RemoveHashedPassword(hashedPassword));
        return this;
    }

    /**
     * Removes any capability from the user.
     *
     * @return {@code this}
     */
    public AclSetuserArgs reset() {
        this.arguments.add(new Reset());
        return this;
    }

    @Override
    public <K, V> void build(CommandArgs<K, V> args) {
        this.arguments.forEach(setuserArg -> setuserArg.build(args));
    }

    private static class CommandSubcommandPair {

        private final CommandType command;
        private final ProtocolKeyword subCommand;

        private CommandSubcommandPair(CommandType command, ProtocolKeyword subCommand) {
            this.command = command;
            this.subCommand = subCommand;
        }

        public CommandType getCommand() {
            return command;
        }

        public ProtocolKeyword getSubCommand() {
            return subCommand;
        }
    }

    /**
     * Internal interface that is the base for all ACL SETUSER arguments.
     */
    private interface SetuserArg {
        <K, V> void build(CommandArgs<K, V> args);
    }

    private static class Active implements SetuserArg {

        private final boolean active;

        Active(boolean active) {
            this.active = active;
        }

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            if (active) {
                args.add(ON);
            } else {
                args.add(OFF);
            }
        }
    }

    private static class Reset implements SetuserArg {

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            args.add(RESET);
        }
    }

    private static class AllKeys implements SetuserArg {

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            args.add(ALLKEYS);
        }
    }

    private static class ResetKeys implements SetuserArg {

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            args.add(RESETKEYS);
        }
    }

    private static class KeyPattern implements SetuserArg {

        private final String keyPattern;

        KeyPattern(String keyPattern) {
            this.keyPattern = keyPattern;
        }

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            args.add("~" + keyPattern);
        }
    }

    private static class ChannelPattern implements SetuserArg {

        private final String channelPattern;

        ChannelPattern(String channelPattern) {
            this.channelPattern = channelPattern;
        }

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            args.add("&" + channelPattern);
        }
    }

    private static class AllChannels implements SetuserArg {

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            args.add(ALLCHANNELS);
        }
    }

    private static class ResetChannels implements SetuserArg {

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            args.add(RESETCHANNELS);
        }
    }

    private static class AllCommands implements SetuserArg {

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            args.add(ALLCOMMANDS);
        }
    }

    private static class AddCommand implements SetuserArg {

        private final CommandSubcommandPair command;

        AddCommand(CommandSubcommandPair command) {
            this.command = command;
        }

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            if (command.getSubCommand() == null) {
                args.add("+" + command.getCommand().name());
            } else {
                args.add("+" + command.getCommand().name() + "|" + command.getSubCommand().name());
            }
        }
    }

    private static class RemoveCommand implements SetuserArg {

        private final CommandSubcommandPair command;

        RemoveCommand(CommandSubcommandPair command) {
            this.command = command;
        }

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            if (command.getSubCommand() == null) {
                args.add("-" + command.getCommand().name());
            } else {
                args.add("-" + command.getCommand().name() + "|" + command.getSubCommand().name());
            }
        }
    }

    private static class NoCommands implements SetuserArg {

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            args.add(NOCOMMANDS);
        }
    }

    private static class AddCategory implements SetuserArg {

        private final AclCategory category;

        AddCategory(AclCategory category) {
            this.category = category;
        }

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            args.add("+@" + category.name());
        }
    }

    private static class RemoveCategory implements SetuserArg {

        private final AclCategory category;

        RemoveCategory(AclCategory category) {
            this.category = category;
        }

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            args.add("-@" + category.name());
        }
    }

    private static class NoPass implements SetuserArg {

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            args.add(NOPASS);
        }
    }

    private static class ResetPass implements SetuserArg {

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            args.add(RESETPASS);
        }
    }

    private static class AddPassword implements SetuserArg {

        private final String password;

        AddPassword(String password) {
            this.password = password;
        }

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            args.add(">" + password);
        }
    }

    private static class AddHashedPassword implements SetuserArg {

        private final String password;

        AddHashedPassword(String password) {
            this.password = password;
        }

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            args.add("#" + password);
        }
    }

    private static class RemovePassword implements SetuserArg {

        private final String password;

        RemovePassword(String password) {
            this.password = password;
        }

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            args.add("<" + password);
        }
    }

    private static class RemoveHashedPassword implements SetuserArg {

        private final String password;

        RemoveHashedPassword(String password) {
            this.password = password;
        }

        @Override
        public <K, V> void build(CommandArgs<K, V> args) {
            args.add("!" + password);
        }
    }
}
