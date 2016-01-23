package me.tekkitcommando.blockops;

/**
 * Copyright (c) 2016 Ethan Smith
 */

public enum GameState {
    LOBBY_STATE(true),
    INGAME_STATE(false),
    GAME_OVER_STATE(false),
    GAME_RESET_STATE(false);

    private boolean allowJoining;
    private static GameState currentState;

    GameState(boolean allowJoining) {
        this.allowJoining = allowJoining;
    }

    public boolean allowJoining() {
        return allowJoining;
    }

    public static void setState(GameState state) {
        GameState.currentState = state;
    }

    public static boolean isState(GameState state) {
        return GameState.currentState == state;
    }

    public static GameState getCurrentState() {
        return currentState;
    }
}
