import React from 'react';
import { FlatList, SafeAreaView, StatusBar, StyleSheet, TouchableOpacity } from 'react-native';
import CartItem from './CartItem';
import { useNavigation } from '@react-navigation/native';

export default function CartItemsList(props) {

    const navigation = useNavigation();

    return (
        <SafeAreaView style={styles.container}>
            <FlatList
                showsVerticalScrollIndicator={false}
                data={props.itemDetails}
                renderItem={({ item }) => (
                    <TouchableOpacity onPress={()=>{
                        navigation.push('ItemDetail', {data: item},
                        );
                    }}>

                        <CartItem itemDetails={item} reRender={props.reRender} setReRender={props.setReRender} />
                    </TouchableOpacity>
                )}
                keyExtractor={item => item.id}
            />
        </SafeAreaView>
    );
}


const styles = StyleSheet.create({
    container: {
        flex: 1,
        marginTop: StatusBar.currentHeight || 0,
    }
});
