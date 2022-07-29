import { Button, Image, StyleSheet, Text, View } from 'react-native';
import Ionicons from 'react-native-vector-icons/Ionicons';


export default function CartItem(props) {
    const cartId = '62dc55563afdd43a90aad170';

    function incrementQuantity() {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ product_id: props.itemDetails.product_id, quantity: 1 })
        };

        fetch('http://35.225.91.83/cart/' + cartId, requestOptions)
            .then((response) => {
                props.setReRender(!props.reRender);
                return response.json();
            }).catch((error) => {
                console.log("incrementQuantity Api call error");
                alert(error.message);
            });
    }

    function decrementQuantity() {
        const requestOptions = {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ product_id: props.itemDetails.product_id, quantity: 1 })
        };

        fetch('http://35.225.91.83/cart/' + cartId + '/' + props.itemDetails.product_id + '/' + 1, requestOptions)
            .then((response) => {
                props.setReRender(!props.reRender);
                return response.json();
            }).catch((error) => {
                console.log("decrementQuantity Api call error");
                alert(error.message);
            });
    }

    function removeProduct() {
        const requestOptions = {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ product_id: props.itemDetails.product_id, quantity: 1 })
        };

        fetch('http://35.225.91.83/cart/' + cartId + '/' + props.itemDetails.product_id + '/' + props.itemDetails.quantity, requestOptions)
            .then((response) => {
                props.setReRender(!props.reRender);
                return response.json();
            }).catch((error) => {
                console.log("removeProduct Api call error");
                alert(error.message);
            });
    }

    return (
        <View style={styles.item_style}>
            <Image
                source={{ uri: props.itemDetails.picture_id }}
                style={styles.pic}
            />

            <View>
                <Text style={styles.words}>{props.itemDetails.product_name}</Text>
                <Text style={styles.words}>brand</Text>
                <Text style={styles.words}>${props.itemDetails.product_price}</Text>
            </View>

            <View style={styles.item_editor}>
                <View style={styles.quantity_editor}>

                    <Button onPress={
                        decrementQuantity}
                        style={[{ width: "90%", margin: 10, backgroundColor: "red" }]}
                        color='black'
                        title="-"
                        accessibilityLabel="Decrement quantity" />

                    <Text style={styles.quantity_text}>{props.itemDetails.quantity}</Text>

                    <Button onPress={incrementQuantity}
                        color='black'
                        title="+"
                        accessibilityLabel="Increment quantity" />
                </View>

                <Ionicons name="trash" size={30} color="black" onPress={removeProduct} />
            </View>

        </View>
    )
}


const styles = StyleSheet.create({
    item_style: {
        backgroundColor: '#fefaf5',
        padding: 12.5,
        marginVertical: 8,
        marginHorizontal: 16,
        borderRadius: 20,
        borderColor: 'black',
        borderTopWidth: .5,
        borderRightWidth: .5,
        borderBottomWidth: 5,
        borderLeftWidth: 5,
        marginBottom: -1,
        flexDirection: 'row',
        alignItems: 'center',
    },

    words: {
        fontSize: 18,
        fontFamily: "Gruppo_400Regular",
        marginBottom: 4,
        marginLeft: 10,
    },

    quantity_text: {
        fontSize: 26,
        fontFamily: "Gruppo_400Regular",
        marginBottom: 4,
        marginLeft: 'auto',
    },

    pic: {
        width: 85,
        height: 85,
        borderRadius: 20,
        marginLeft: -2.5,
        borderColor: 'black',
        borderWidth: .5,
    },

    item_editor: {
        marginLeft: 'auto',
        flexDirection: 'row',
        alignItems: 'center',
        marginRight: 10,
    },

    quantity_editor: {
        flexDirection: 'row',
        alignItems: 'center',
        marginRight: 10,
    },

    button_text: {
        fontSize: 26,
    }
});
