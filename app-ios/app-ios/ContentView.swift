import SwiftUI
import shared

struct ContentView: View {
    var body: some View {
        ComposeView().ignoresSafeArea(.all, edges: .bottom)
    }
}

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> some UIViewController {
        App_iosKt.AppIOSView()
    }
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        
    }
}

