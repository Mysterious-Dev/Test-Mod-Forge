package fr.lmf.test_mod_forge.utils;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ModConfig {

    public static class Client {

        public final ForgeConfigSpec.BooleanValue showGuiOnDeath;

        Client(ForgeConfigSpec.Builder builder){
            builder.comment("Client Configuration for TestMod")
                    .push("client");

            showGuiOnDeath = builder.define("guiOnDeath", true);

            builder.pop();
        }

    }

    public static class Server {

        public final ForgeConfigSpec.IntValue timeoutAFK;
        public final ForgeConfigSpec.IntValue damageCustomSword;

        Server(ForgeConfigSpec.Builder builder) {

            builder.comment("Server configuration")
                    .push("server");

            builder.comment("Amount of seconds before player timeout");
            timeoutAFK = builder.defineInRange("timeoutAFK", 300, 0, 3600);

            builder.comment("Amount of damage for the custom sword");
            damageCustomSword = builder.defineInRange("dmgCustomSword", 4, 4, 10);

            builder.pop();
        }
    }

    public static class Common{

        public final ForgeConfigSpec.IntValue damageCustomSword;

        Common(ForgeConfigSpec.Builder builder) {

            builder.comment("Common configuration")
                    .push("common");

            builder.comment("Amount of damage for the custom sword");
            damageCustomSword = builder.defineInRange("dmgCustomSword", 4, 4, 10);

            builder.pop();

        }

    }

    public static final ForgeConfigSpec clientSpec;
    public static final Client client;

    static {
        final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
        client = specPair.getLeft();
        clientSpec = specPair.getRight();
    }

    public static final ForgeConfigSpec serverSpec;
    public static final Server server;

    static {
        final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Server::new);
        server = specPair.getLeft();
        serverSpec = specPair.getRight();
    }

    public static final ForgeConfigSpec commonSpec;
    public static final Common common;

    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        common = specPair.getLeft();
        commonSpec = specPair.getRight();
    }

}
