package net.themcbrothers.uselessmod.api;

import com.google.common.collect.Maps;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Function;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.LIT;

/**
 * This class is used for registering Lamp states to the Light Switch
 */
public class LampRegistry {
    private static final Map<Block, Pair<Function<BlockState, BlockState>, Function<BlockState, BlockState>>> LAMPS_MAP = Maps.newHashMap();

    /**
     * Returns the BlockState of the given Key when it would be turned on or off.
     * Can return NULL when the given key has not been registered via registerLampState
     *
     * @param state  Key state
     * @param turnOn Should be TRUE if you want to return the ON state, otherwise FALSE
     * @return Lamp state (ON or OFF) or NULL if the key doesn't exist
     */
    @Nullable
    public static BlockState getLampState(BlockState state, boolean turnOn) {
        if (!isLampRegistered(state)) {
            return null;
        }

        Pair<Function<BlockState, BlockState>, Function<BlockState, BlockState>> pair = LAMPS_MAP.get(state.getBlock());
        return turnOn ? pair.getLeft().apply(state) : pair.getRight().apply(state);
    }

    /**
     * Returns TRUE when the block of the state has been registered via registerLampState
     *
     * @param state Block State
     * @return TRUE or FALSE
     */
    public static boolean isLampRegistered(BlockState state) {
        return LAMPS_MAP.containsKey(state.getBlock());
    }

    /**
     * Use this method to register lamps so that the light switch can interact with them
     *
     * @param block Block with {@link BlockStateProperties#LIT} property on his block states
     */
    public static void registerLampState(Block block) {
        registerLampState(block,
                state -> state.hasProperty(LIT) ? state.setValue(LIT, Boolean.TRUE) : state,
                state -> state.hasProperty(LIT) ? state.setValue(LIT, Boolean.FALSE) : state);
    }

    /**
     * Use this method to register lamps so that the light switch can interact with them
     *
     * @param block   Block
     * @param turnOn  Function to turn the Lamp or Block ON
     * @param turnOff Function to turn the Lamp or Block OFF
     */
    public static void registerLampState(Block block, Function<BlockState, BlockState> turnOn, Function<BlockState, BlockState> turnOff) {
        LAMPS_MAP.put(block, Pair.of(turnOn, turnOff));
    }
}
