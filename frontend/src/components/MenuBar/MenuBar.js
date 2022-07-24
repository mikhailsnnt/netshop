import styles from './MenuBar.module.css'
import {faShoppingCart, faRightToBracket, faUser} from "@fortawesome/free-solid-svg-icons";
import 'font-awesome-animation/css/font-awesome-animation.min.css'
import SearchBar from "../searchbar/SearchBar";
import MenuBarItem from "./MenuBarItem";
import CatalogDropdown from "./CatalogDropdown/CatalogDropdown";


export default function MenuBar(props) {
    return (
        <ul className={styles.wrapper}>
            <CatalogDropdown/>
            <li>
                <SearchBar/>
            </li>
            <MenuBarItem linkTo="/cart" fontAwesomeIcon={faShoppingCart}/>
            {props.isAuthenticated ? <MenuBarItem linkTo="/profile" fontAwesomeIcon={faUser}/>
                : <MenuBarItem linkTo="/login" fontAwesomeIcon={faRightToBracket}/>}
        </ul>
    )
}

