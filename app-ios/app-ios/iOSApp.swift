import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        DependencyGraph().load()
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
