import { Ionicons, MaterialCommunityIcons } from "@expo/vector-icons";
import React, { useState } from "react";
import { Image, Pressable, StyleSheet, Text, TouchableOpacity, View } from 'react-native';


export default function Product(props) {
    const [liked, setLiked] = useState(false);
    const [likedCart, setLikedCart] = useState(false);
    const cartId = '62dc55563afdd43a90aad170';

    const addToCart = (productID) => {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ product_id: productID, quantity: 1 })
        };

        fetch('http://35.225.91.83/cart/' + cartId, requestOptions)
            .then((response) => {
                return response.json();
            }).catch((error) => {
                console.log("Api call error");
                alert(error.message);
            });
    }

    return (
        <View style={[styles.container, { flexDirection: "column" }]}>
            <View style={{ flex: 1 }}>
                <View>
                    <Image style={styles.imageView}
                        source={{ uri: props.itemData.picture_id }}
                        resizeMode='stretch'
                    />
                </View>

                <View >
                    <TouchableOpacity style={styles.button} onPress={() => {
                                props.navigation.push('TryOn');
                            }}>
                        <Ionicons style={styles.glasses} name="md-glasses-outline" size={30} ios="ios-happy-outline" android="md-happy" />
                        <View >
                            <Text style={styles.tryOnText}>Try them On</Text>
                        </View>
                    </TouchableOpacity>
                </View>

                <View >

                    <View style={styles.label}>
                        <View style={{ marginLeft: -30 }}>

                            <View>
                                <Text style={styles.text}>{props.itemData.product_name}</Text>
                                <Text style={styles.text}>{props.itemData.brand}</Text>
                                <Text style={styles.text}>${props.itemData.product_price}</Text>
                            </View>

                            <Pressable style={styles.buttont} onPress={() => setLiked((isLiked) => !isLiked)}>
                                <Ionicons
                                    style={styles.hearto} name={liked ? "heart" : "heart-outline"} size={55} color='#D82130' />
                            </Pressable>

                            <Pressable style={styles.buttont} onPress={
                                () => {
                                    setLikedCart((isLiked) => !isLiked);
                                    if (!likedCart) {
                                        addToCart(props.itemData.product_id)
                                    }
                                }}>

                                <MaterialCommunityIcons
                                    style={styles.cart} name={likedCart ? "cart-check" : "cart-plus"} size={45} />
                            </Pressable>

                        </View>
                    </View>

                </View>

            </View>
        </View>
    );
}


const styles = StyleSheet.create({
    container: {
        flex: 1,
        marginTop: 14,
        alignItems: 'center'
    },

    imageView: {
        width: 394,
        height: 437,
        borderRadius: 20,
        alignContent: 'center'
    },

    text: {
        fontSize: 18,
        fontFamily: "Gruppo_400Regular",
        paddingTop: 8,
        paddingLeft: 33,
        color: '#D82130',
        marginRight: 45,
    },

    hearto: {
        marginTop: -62,
        marginLeft: 248,
    },

    cart: {
        marginTop: -58,
        marginLeft: 313,
        color: '#D82130'
    },

    label: {
        height: 90,
        width: 371,
        backgroundColor: '#fffbf6',
        borderRadius: 20,
        borderColor: '#000000',
        borderWidth: 1,
        left: 12,
        marginTop: -105,
        alignItems: 'center'
    },

    glasses: {
        marginTop: 1,
        marginLeft: '-64%',
        color: '#D82130'
    },

    button: {
        backgroundColor: '#E9FFFF',
        color: '#fff',
        justifyContent: 'center',
        alignItems: 'center',
        marginTop: -110,
        left: 121,
        borderRadius: 30,
        marginTop: -175,
        height: 52,
        width: 161,
        borderColor: 'black',
        borderTopWidth: .8,
        borderRightWidth: .5,
        borderBottomWidth: 5,
        borderLeftWidth: 5,
    },

    buttont: {
        borderRadius: 30,
        opacity: 4,
        shadowRadius: 3,
        shadowColor: '#000000',
        shadowOffset: { width: -5, height: 6 },
    },

    tryOnText: {
        marginTop: -24,
        color: '#D82130',
        fontSize: 12,
        marginLeft: '20%',
        fontWeight: '500'
    },
})

