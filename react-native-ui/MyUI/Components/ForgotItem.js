import React from 'react';
import { Image, StyleSheet, View } from 'react-native';


export default function ForgotItem(props) {
    return (
        <View>
            <Image
                source={{ uri: props.item.picture_id }}
                style={styles.forgPic}
            />
        </View>
    )
}


const styles = StyleSheet.create({
    forgPic: {
        height: 130,
        width: 130,
        backgroundColor: '#fefaf5',
        padding: 20,
        marginVertical: 9.25,
        marginStart: 9.25, // Change this to actually work
        borderRadius: 20,
        borderColor: 'black',
        borderWidth: .75,
    }
});