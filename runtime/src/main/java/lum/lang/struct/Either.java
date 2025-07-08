package lum.lang.struct;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A functional Either type representing a Union of two possible types A or B.
 * An Either instance contains exactly one value of either type A (left) or type B (right).
 * 
 * By convention, Left is used for error/failure cases and Right for success cases,
 * making Either right-biased for monadic operations.
 */
public abstract sealed class Either<A, B> {
    
    // === Basic accessors ===
    
    /**
     * @return true if this Either contains a Left value
     */
    public abstract boolean isLeft();
    
    /**
     * @return true if this Either contains a Right value
     */
    public abstract boolean isRight();
    
    /**
     * @return the Left value if present, null otherwise
     */
    public abstract A left();
    
    /**
     * @return the Right value if present, null otherwise
     */
    public abstract B right();
    
    // === Factory methods ===
    
    /**
     * Creates a Left Either
     */
    public static <A, B> Either<A, B> left(A value) {
        return new Left<>(value);
    }
    
    /**
     * Creates a Right Either
     */
    public static <A, B> Either<A, B> right(B value) {
        return new Right<>(value);
    }
    
    /**
     * Creates an Either from a nullable value, using Left for null
     */
    public static <A, B> Either<A, B> ofNullable(B value, A leftValue) {
        return value != null ? right(value) : left(leftValue);
    }
    
    /**
     * Creates an Either from a nullable value, using Left supplier for null
     */
    public static <A, B> Either<A, B> ofNullable(B value, Supplier<A> leftSupplier) {
        return value != null ? right(value) : left(leftSupplier.get());
    }
    
    // === Monadic operations (right-biased) ===
    
    /**
     * Maps the Right value if present, otherwise returns the Left unchanged
     */
    public abstract <C> Either<A, C> map(Function<? super B, ? extends C> mapper);
    
    /**
     * FlatMaps the Right value if present, otherwise returns the Left unchanged
     */
    public abstract <C> Either<A, C> flatMap(Function<? super B, ? extends Either<A, C>> mapper);
    
    /**
     * Maps the Left value if present, otherwise returns the Right unchanged
     */
    public abstract <C> Either<C, B> mapLeft(Function<? super A, ? extends C> mapper);
    
    /**
     * FlatMaps the Left value if present, otherwise returns the Right unchanged
     */
    public abstract <C> Either<C, B> flatMapLeft(Function<? super A, ? extends Either<C, B>> mapper);
    
    // === Filtering ===
    
    /**
     * Filters the Right value with a predicate. If predicate fails, converts to Left
     */
    public abstract Either<A, B> filter(Predicate<? super B> predicate, Supplier<A> leftSupplier);
    
    /**
     * Filters the Right value with a predicate. If predicate fails, converts to Left
     */
    public Either<A, B> filter(Predicate<? super B> predicate, A leftValue) {
        return filter(predicate, () -> leftValue);
    }
    
    // === Folding and reduction ===
    
    /**
     * Folds this Either into a single value using the appropriate mapper
     */
    public <T> T fold(Function<? super A, ? extends T> leftMapper, Function<? super B, ? extends T> rightMapper) {
        return isLeft() ? leftMapper.apply(left()) : rightMapper.apply(right());
    }
    
    /**
     * Returns the Right value or the provided default
     */
    public B getOrElse(B defaultValue) {
        return isRight() ? right() : defaultValue;
    }
    
    /**
     * Returns the Right value or the result of the supplier
     */
    public B getOrElse(Supplier<B> supplier) {
        return isRight() ? right() : supplier.get();
    }
    
    /**
     * Returns this Either if Right, otherwise returns the alternative
     */
    public Either<A, B> orElse(Either<A, B> alternative) {
        return isRight() ? this : alternative;
    }
    
    /**
     * Returns this Either if Right, otherwise returns the result of the supplier
     */
    public Either<A, B> orElse(Supplier<Either<A, B>> supplier) {
        return isRight() ? this : supplier.get();
    }
    
    // === Side effects ===
    
    /**
     * Performs an action on the Left value if present
     */
    public Either<A, B> peekLeft(Consumer<? super A> action) {
        if (isLeft()) action.accept(left());
        return this;
    }
    
    /**
     * Performs an action on the Right value if present
     */
    public Either<A, B> peek(Consumer<? super B> action) {
        if (isRight()) action.accept(right());
        return this;
    }
    
    /**
     * Performs the appropriate action based on which value is present
     */
    public Either<A, B> peek(Consumer<? super A> leftAction, Consumer<? super B> rightAction) {
        if (isLeft()) leftAction.accept(left());
        else rightAction.accept(right());
        return this;
    }
    
    // === Conversions ===
    
    /**
     * Converts to Optional, keeping only Right values
     */
    public Optional<B> toOptional() {
        return isRight() ? Optional.of(right()) : Optional.empty();
    }
    
    /**
     * Converts to Optional of Left values
     */
    public Optional<A> toOptionalLeft() {
        return isLeft() ? Optional.of(left()) : Optional.empty();
    }
    
    /**
     * Swaps Left and Right
     */
    public Either<B, A> swap() {
        return isLeft() ? right(left()) : left(right());
    }

    public static final class Left<A, B> extends Either<A, B> {

        private final A value;

        public Left(A value) {
            this.value = value;
        }

        @Override
        public boolean isLeft() {
            return true;
        }
        
        @Override
        public boolean isRight() {
            return false;
        }

        @Override
        public A left() {
            return value;
        }
        
        @Override
        public B right() {
            return null;
        }
        
        @Override
        public <C> Either<A, C> map(Function<? super B, ? extends C> mapper) {
            return new Left<>(value);
        }
        
        @Override
        public <C> Either<A, C> flatMap(Function<? super B, ? extends Either<A, C>> mapper) {
            return new Left<>(value);
        }
        
        @Override
        public <C> Either<C, B> mapLeft(Function<? super A, ? extends C> mapper) {
            return new Left<>(mapper.apply(value));
        }
        
        @Override
        public <C> Either<C, B> flatMapLeft(Function<? super A, ? extends Either<C, B>> mapper) {
            return mapper.apply(value);
        }
        
        @Override
        public Either<A, B> filter(Predicate<? super B> predicate, Supplier<A> leftSupplier) {
            return this;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Left<?, ?> other)) return false;
            return Objects.equals(value, other.value);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash("Left", value);
        }
        
        @Override
        public String toString() {
            return "Left(" + value + ")";
        }
    }

    public static final class Right<A, B> extends Either<A, B> {
        private final B value;

        public Right(B value) {
            this.value = value;
        }

        @Override
        public boolean isLeft() {
            return false;
        }
        
        @Override
        public boolean isRight() {
            return true;
        }

        @Override
        public A left() {
            return null;
        }
        
        @Override
        public B right() {
            return value;
        }
        
        @Override
        public <C> Either<A, C> map(Function<? super B, ? extends C> mapper) {
            return new Right<>(mapper.apply(value));
        }
        
        @Override
        public <C> Either<A, C> flatMap(Function<? super B, ? extends Either<A, C>> mapper) {
            return mapper.apply(value);
        }
        
        @Override
        public <C> Either<C, B> mapLeft(Function<? super A, ? extends C> mapper) {
            return new Right<>(value);
        }
        
        @Override
        public <C> Either<C, B> flatMapLeft(Function<? super A, ? extends Either<C, B>> mapper) {
            return new Right<>(value);
        }
        
        @Override
        public Either<A, B> filter(Predicate<? super B> predicate, Supplier<A> leftSupplier) {
            return predicate.test(value) ? this : new Left<>(leftSupplier.get());
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Right<?, ?> other)) return false;
            return Objects.equals(value, other.value);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash("Right", value);
        }
        
        @Override
        public String toString() {
            return "Right(" + value + ")";
        }
    }
}