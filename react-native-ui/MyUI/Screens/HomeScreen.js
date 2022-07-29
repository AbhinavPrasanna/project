import { Gruppo_400Regular, useFonts } from "@expo-google-fonts/gruppo";
import * as React from 'react';
import { FlatList, SafeAreaView, StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import CustomSearchBar from '../Components/CustomSearchBar';
import Filter from '../Components/Filter';
import Product from '../Components/Product';
import { useFocusEffect } from '@react-navigation/native';


export default function HomeScreen({ navigation }) {
    let [fontsLoaded] = useFonts({
        Gruppo_400Regular
    });
    const [searchQuery, setSearchQuery] = React.useState('');
    const [FilteredProducts,setFilteredProducts] = React.useState([]);
    const [products, setProducts] = React.useState([]);
    
    React.useEffect(() => {
        fetch("http://35.225.91.83/product")
            .then((response) => {
                return response.json();
            })
            .then((data) => {
                setProducts(data)
                setFilteredProducts(data)
            })
            .catch((error) => {
                console.log("Api call error");
                alert(error.message);
            });
    }, [])
    onChangeSearch = (query) =>{
        setSearchQuery(query.toLowerCase());
        setFilteredProducts(products.filter(product => product.product_name.startsWith(searchQuery.toLowerCase())))
    } 
    if (fontsLoaded) {
        return (
            <View style={{ backgroundColor: '#f4f4f4' }}>
                <Text
                    onPress={() => alert('This is the "Home" screen.')}
                    style={styles.iWare}>iWare
                </Text>

                <View>
                    <CustomSearchBar onChangeSearch={onChangeSearch} searchQuery={searchQuery} />
                    <Filter />
                </View>

                <SafeAreaView style={styles.products_container}>

                    <FlatList
                        data={FilteredProducts}
                        renderItem={({ item }) => (
                            <TouchableOpacity onPress={() => {
                                navigation.push('ItemDetail', { data: item },
                                );
                            }}>
                                <Product itemData={item} navigation = {navigation} />
                            </TouchableOpacity>
                        )}
                        keyExtractor={item => item.product_id}
                        showsVerticalScrollIndicator={false}
                    />

                </SafeAreaView>

            </View>
        );
    }
    else {
        return <Text>Error</Text>;
    }
}

const styles = StyleSheet.create({
    products_container: {
        marginTop: -30,
    },

    iWare: {
        fontSize: 36,
        fontFamily: "Gruppo_400Regular",
        paddingTop: 48,
        paddingLeft: 13,
    }
})
