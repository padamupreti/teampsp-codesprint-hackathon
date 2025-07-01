import Navbar from "./Navbar"
import Container from "./Container"

function Layout({ children }) {
    return (
        <>
            <Navbar />
            <Container>{children}</Container>
        </>
    )
}
export default Layout
