import {faBarsStaggered} from "@fortawesome/free-solid-svg-icons";
import MenuBarItem from "../MenuBarItem";
import {Link} from "react-router-dom";
import classes from "./CatalogDropdown.module.css"


function CatalogDropDownContent(props) {
    const {isRoot = true} = props
    const menuClasses = `${classes['catalog-menu']} ${!isRoot?classes['catalog-sub-menu']:''}`
    return (
        <ul className={menuClasses}>
            {props.categories.map(category =>
                <li key={category.id} className={classes['catalog-item']}>
                    <Link to={`/catalog/${category.id}`} className={classes['catalog-item-link']}> {category.name}  </Link>
                    {category.children && <> <CatalogDropDownContent categories={category.children}  isRoot={false}/> </>}
                </li>
            )}
        </ul>
    )
}


function CatalogDropdown(props) {
    //TODO REPLACE DUMMY DATA WITH API CALL
    const dummyData = [
        {id: 1, name: "Drinks"},
        {id: 2, name: "Fruits", children: [{id: 10, name:"Melons", children:[{id:13,name:"Watermelons"}]}, {id: 12, name: "Berries"}]},
        {id: 3, name: "Bikes"},
        {id: 4, name: "Books"},
    ]
    return (
        <div className={classes.wrapper}>
            <MenuBarItem linkTo="/catalog" fontAwesomeIcon={faBarsStaggered}/>
            <CatalogDropDownContent categories={dummyData}/>
        </div>
    )
}

export default CatalogDropdown
