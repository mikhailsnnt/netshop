import TextField from "@mui/material/TextField";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faMagnifyingGlass} from "@fortawesome/free-solid-svg-icons/faMagnifyingGlass";
import styles from './SearcBar.module.css'
import {InputAdornment} from "@mui/material";
import {useState} from "react";

export default function SearchBar() {
    const [isSelected, setIsSelected] = useState(false)
    return (
        <div className={styles.wrapper}>
            <TextField
                id="outlined-basic"
                // onChange={inputHandler}
                fullWidth
                label="Search"
                className="ui-font"
                InputProps={{
                    endAdornment:
                        (
                        <InputAdornment  position="end">
                            <FontAwesomeIcon className={`${styles['search-icon']} ${isSelected?styles['search-icon-hidden']:''}`} icon={faMagnifyingGlass} size="2x"/>
                        </InputAdornment>
                        )

                }}
                onFocus={() => setIsSelected(true)}
                onBlur={() => setIsSelected(false)}
            />
        </div>
    )
}
