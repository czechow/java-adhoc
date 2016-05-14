package pl.czechow;

import pl.czechow.functional.Either;

public class Main {

    public static void main(String[] args) {
        System.out.println("=============> Either");

        Either<String, Integer> me = Either.right(123);
        Either<String, Integer> me3 = me
                .flatMap(i -> Either.right(i + 3))
                .flatMap(i -> Either.left("Error"))
                .flatMap(i -> Either.right(1233));
        System.out.println(me3);

        Either<String, String> output =
                Either.fromNullableRight("No secId", 123).flatMap(secId ->
                Either.fromNullableRight("No date", 456).flatMap(d ->
                Either.right("Object constructed from " + secId + " and " + d)));

        System.out.println("Output is " + output);
    }
}
