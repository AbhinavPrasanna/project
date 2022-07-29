import { AntDesign } from "@expo/vector-icons";
import * as React from 'react';
import { StyleSheet } from 'react-native';


export default function Filter() {
    return(
        <AntDesign style={styles.filter} name="filter" size={30} />
    )
}


const styles = StyleSheet.create({
    filter: {
        left: 370,
        bottom: 56
    }
})