import { useFocusEffect } from '@react-navigation/native';
import * as React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import CartItemList from '../Components/CartItemsList';
import CartModal from '../Components/CartModal';
import ForgetList from '../Components/ForgetList';


const name = 'User';
const cartId = '62dc55563afdd43a90aad170';
const localHost = 'localhost:8080';
const femsHosted = '35.225.91.83';


export default function CartScreen(props) {

    const [cart, setCart] = React.useState([]);
    const [reRender, setReRender] = React.useState(false);
    const [price, setPrice] = React.useState(0);

    useFocusEffect(
        React.useCallback(() => {
            fetch("http://" + femsHosted + "/cart/" + cartId + "/products")
                .then((response) => {
                    return response.json();
                }).then((data) => {
                    if (data.length > 0) {
                        props.setIsCartFull(true);
                    } else {
                        props.setIsCartFull(false);
                    }

                    setCart(data);
                    setPrice(0);

                    let temp_price = 0;
                    data.map((item) => {
                        temp_price = temp_price + (item.product_price * item.quantity)
                    })

                    setPrice(temp_price);

                }).catch((error) => {
                    console.log("CartScreen Api call error");
                    alert(error.message);
                });
        }, [cart.length, reRender])
    );

    return (
        <View>
            <Text style={styles.iWare}>
                {name}'s Cart
            </Text>

            <Text style={styles.ListHeader}>
                Forget Anything?
            </Text>

            <View style={styles.container}>
                <ForgetList cartitems={cart} />
            </View>

            <Text style={styles.ListHeader}>
                Your Items
            </Text>

            <View style={styles.container2}>
                <CartItemList itemDetails={cart} setReRender={setReRender} reRender={reRender} navigation={props.navigation} />
            </View>

            <View>
                <CartModal price={price} />
            </View>

        </View>
    );
}


const styles = StyleSheet.create({
    container: {
        height: 150,
        backgroundColor: '#fcecf5',
        marginVertical: 11,
        marginHorizontal: 16,
        borderRadius: 20,
        borderColor: 'black',
        borderWidth: .75,
        overflow: 'hidden',
    },

    container2: {
        height: '46.5%',
        marginVertical: 2,
        justifyContent: 'center',
        paddingBottom: '8%',
    },

    iWare: {
        fontSize: 36,
        fontFamily: "Gruppo_400Regular",
        paddingTop: 48,
        paddingLeft: 13,
        paddingBottom: 13,
    },

    ListHeader: {
        fontWeight: 'bold',
        fontSize: 20,
        paddingLeft: 13,
        paddingTop: 0,
    }
});
