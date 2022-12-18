package fr.lmf.test_mod_forge.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;

import java.util.Collection;

public class TestCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext p_214732_) {

        dispatcher.register(
                Commands.literal("setfire")
                        .requires((p_137777_) -> {
                            return p_137777_.hasPermission(2);
                        })
                        .then(
                                Commands.literal("entities")
                                        .then(
                                                Commands.argument("targets", EntityArgument.entities())
                                                        .executes(ctx -> setFireEntities(ctx.getSource(), EntityArgument.getOptionalEntities(ctx, "targets"), 5))
                                                        .then(
                                                                Commands.argument("duration", IntegerArgumentType.integer(0))
                                                                        .executes(ctx -> setFireEntities(ctx.getSource(), EntityArgument.getOptionalEntities(ctx, "targets"), IntegerArgumentType.getInteger(ctx, "duration")))
                                                        )
                                        )
                        )
                        .then(
                                Commands.literal("block")
                                        .then(
                                                Commands.argument("pos", BlockPosArgument.blockPos())
                                                        .executes(ctx -> setFireBlock(ctx.getSource(), BlockPosArgument.getLoadedBlockPos(ctx, "pos")))
                                        )
                        )
        );

    }

    private static int setFireEntities(CommandSourceStack src, Collection<? extends Entity> targets, int duration)
    {
        targets.forEach(e -> e.setSecondsOnFire(duration));

        src.sendSuccess(Component.translatable("commands.banlist.none"), false);

        return targets.size();
    }

    private static int setFireBlock(CommandSourceStack src, BlockPos pos)
    {
        ServerLevel serverlevel = src.getLevel();

       if(!serverlevel.isClientSide)
           serverlevel.setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());

        src.sendSuccess(Component.translatable("commands.banlist.none"), false);

        return 1;
    }

}
