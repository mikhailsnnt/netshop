import styles from './MenuBarItem.module.css'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {Link} from "react-router-dom";

const MenuBarItem = props => (
    <li>
        <Link to = {props.linkTo}>
            <FontAwesomeIcon icon={props.fontAwesomeIcon} className={`${styles['item-icon']} faa-float animated-hover`} size="2x" />
        </Link>
    </li>
)

export default MenuBarItem