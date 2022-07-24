import styles from './MenuBar.module.css'
import {faShoppingCart, faBarsStaggered, faRightToBracket, faUser} from "@fortawesome/free-solid-svg-icons";
import 'font-awesome-animation/css/font-awesome-animation.min.css'
import SearchBar from "../searchbar/SearchBar";
import MenuBarItem from "./MenuBarItem";


export default function MenuBar(props) {
    return (
        <ul className={styles.wrapper}>
            <MenuBarItem linkTo="/catalog" fontAwesomeIcon={faBarsStaggered}/>
            <li>
                <SearchBar/>
            </li>
            <MenuBarItem linkTo="/cart" fontAwesomeIcon={faShoppingCart}/>
            {props.isAuthenticated ? <MenuBarItem linkTo="/profile" fontAwesomeIcon={faUser}/>
                : <MenuBarItem linkTo="/login" fontAwesomeIcon={faRightToBracket}/>}
        </ul>
    )
}

