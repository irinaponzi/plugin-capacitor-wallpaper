// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "Wall",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "Wall",
            targets: ["WallpaperPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "WallpaperPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/WallpaperPlugin"),
        .testTarget(
            name: "WallpaperPluginTests",
            dependencies: ["WallpaperPlugin"],
            path: "ios/Tests/WallpaperPluginTests")
    ]
)