import "@/styles/globals.css"

import Navbar from "@/components/Navbar"
import Container from "@/components/Container"

export default function App({ Component, pageProps }) {
    return (
        <>
            <Navbar />
            <Container>
                <Component {...pageProps} />
            </Container>
        </>
    )
}
