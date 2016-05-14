package pl.czechow.functional;


import java.util.function.Function;

public interface Either<L, R> {

    L getLeft();
    R getRight();

    boolean isLeft();
    boolean isRight();

    static <L, R> Either<L, R> left(L l) {
        return new Left<>(l);
    }

    static <L, R> Either<L, R> right(R r) {
        return new Right<>(r);
    }

    static <L, R> Either<L, R> fromNullableRight(L l, R r) {
        if (r != null) return right(r);
        else return left(l);
    }

    <R2> Either<L, R2> map(Function<R, R2> f);
    <R2> Either<L, R2> flatMap(Function<R, Either<L, R2>> f);
}

class Left<L, R> implements Either<L, R> {
    L l;

    Left(L l) { this.l = l; }

    @Override public L getLeft() { return l; }
    @Override public R getRight() { throw new IllegalArgumentException("Right value is undefined"); }

    @Override public boolean isLeft() { return true; }
    @Override public boolean isRight() { return false; }

    @Override
    public <R2> Either<L, R2> map(Function<R, R2> f) {
        return new Left<>(l);
    }

    @Override
    public <R2> Either<L, R2> flatMap(Function<R, Either<L, R2>> f) { return new Left<>(l); }

    public String toString() { return "Left(" + l + ")"; }
}

class Right<L, R> implements Either<L, R> {
    R r;

    Right(R r) { this.r = r; }

    @Override public L getLeft() { throw new IllegalArgumentException("Left value is undefined"); }
    @Override public R getRight() { return r; }

    @Override public boolean isLeft() { return false; }
    @Override public boolean isRight() { return true; }

    @Override
    public <R2> Either<L, R2> map(Function<R, R2> f) {
        return new Right<>(f.apply(r));
    }

    @Override
    public <R2> Either<L, R2> flatMap(Function<R, Either<L, R2>> f) {
        return f.apply(r);
    }

    public String toString() { return "Right(" + r + ")"; }
}


