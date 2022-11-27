package org.unknown.phoenix;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebDriver;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.net.URL;

public final class Configuration {

    private static @Nullable Path pathToDownloads;

    private static @Nullable WebDriver webDriver;

    private static @Nullable URL remoteAddress;

    public static @NotNull Path getPathToDownloads() {
        if (pathToDownloads == null)
            throw new NullPointerException("Путь к загрузкам не был инициализирован.");
        return pathToDownloads;
    }

    public static @NotNull WebDriver getWebDriver() {
        if (webDriver == null)
            throw new NullPointerException("Драйвер браузера не был инициализирован.");
        return webDriver;
    }

    public static @NotNull URL getRemoteAddress() {
        if (remoteAddress == null)
            throw new NullPointerException("Удалённый адрес не был инициализирован.");
        return remoteAddress;
    }

    public static void configure(@NotNull RemoteWebDriver remoteWebDriver, @NotNull Path pathToDownloads, @NotNull URL remoteAddress) {
        setup(remoteWebDriver, pathToDownloads, remoteAddress);
    }

    public static void configure(@NotNull WebDriver webDriver, @NotNull Path pathToDownloads) {
        setup(webDriver, pathToDownloads, null);
    }

    private static void setup(@NotNull WebDriver webDriver, @NotNull Path pathToDownloads, @Nullable URL remoteAddress) {
        final var folderForDownloads = pathToDownloads.toFile();

        if (folderForDownloads.isFile())
            throw new IllegalArgumentException("Папка для загрузок не может являться файлом.");

        if (!folderForDownloads.exists() && !folderForDownloads.mkdirs())
            throw new IllegalArgumentException("Не удалось создать папку для загрузок.");

        Configuration.pathToDownloads = pathToDownloads;
        Configuration.remoteAddress = remoteAddress;
        Configuration.webDriver = webDriver;
    }
}
