// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "Installerplugin",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "Installerplugin",
            targets: ["InstallerPluginPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "InstallerPluginPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/InstallerPluginPlugin"),
        .testTarget(
            name: "InstallerPluginPluginTests",
            dependencies: ["InstallerPluginPlugin"],
            path: "ios/Tests/InstallerPluginPluginTests")
    ]
)