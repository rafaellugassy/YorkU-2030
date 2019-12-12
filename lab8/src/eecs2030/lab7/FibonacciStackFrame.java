package eecs2030.lab7;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * A stack frame for computing Fibonacci numbers.
 * 
 * @author EECS2030 Fall 2016-17
 *
 */
public class FibonacciStackFrame {

	private static Map<Integer, BigInteger> cache = new HashMap<Integer, BigInteger>();
	private int n;
	private FibonacciStackFrame caller;
	private boolean fMinus1Computed;
	private boolean fMinus2Computed;
	private BigInteger fMinus1;
	private BigInteger fMinus2;
	private BigInteger sum;

	/**
	 * Creates a stack frame to compute F(n). <code>caller</code> is a reference
	 * to the <code>FibonacciStackFrame</code> that created this stack frame. If
	 * this stack frame is not being created by another
	 * <code>FibonacciStackFrame</code> instance, then <code>caller</code> is
	 * expected to be equal to <code>null</code>.
	 * 
	 * 
	 * @param n
	 *            the Fibonccci number to compute
	 * @param caller
	 *            the <code>FibonacciStackFrame</code> that created this stack
	 *            frame
	 * @throws IllegalArgumentException
	 *             if n is less than 0
	 */
	public FibonacciStackFrame(int n, FibonacciStackFrame caller) {
		// MAKE SURE THAT YOU SET ALL OF THE NON-STATIC FIELDS OF THIS CLASS
		if (n < 0)
			throw new IllegalArgumentException();
		this.n = n;
		this.caller = caller;
		this.fMinus1 = BigInteger.ZERO;
		this.fMinus2 = BigInteger.ZERO;
		this.sum = BigInteger.ZERO;
		this.fMinus1Computed = false;
		this.fMinus2Computed = false;

	}

	/**
	 * Receive a return value from another <code>FibonacciStackFrame</code>
	 * instance.
	 * 
	 * <p>
	 * This method is used to simulate Lines 16 and 17 of the fib(int) method
	 * described in the lab web page. This method needs to figure out if it is
	 * simulating Line 16 or Line 17; continue reading for details.
	 * </p>
	 * 
	 * <p>
	 * If this.fMinus1Computed is false, then this method should set
	 * this.fMinus1 to <code>retVal</code> (simulating Line 16), and set
	 * this.fMinus1Computed to true.
	 * </p>
	 * 
	 * <p>
	 * If this.fMinus1Computed is true, and this.fMinus2Computed is false, then
	 * this method should set this.fMinus2 to <code>retVal</code> (simulating
	 * Line 17), and set this.fMinus2Computed to true..
	 * </p>
	 * 
	 * <p>
	 * If both this.fMinus1Computed and this.fMinus2Computed are true then you
	 * have done something wrong elsewhere in the class, and you should throw an
	 * IllegalStateException.
	 * </p>
	 * 
	 * @param retVal
	 *            the value of F(n - 1) or F(n - 2) returned from another
	 *            <code>FibonacciStackFrame</code> instance
	 * @throws IllegalStateException
	 *             if this stack frame has already received values for both F(n
	 *             - 1) and F(n - 2)
	 */
	public void receiveReturnValue(BigInteger retVal) {
		if (!this.fMinus1Computed) {
			this.fMinus1 = retVal;
			this.fMinus1Computed = true;
		}

		else if (!this.fMinus2Computed) {
			this.fMinus2 = retVal;
			this.fMinus2Computed = true;
		}

		else
			throw new IllegalStateException();
	}

	/**
	 * Returns the value of F(this.n) back to the caller. This simulates Line 21
	 * of the fib(int) method described in the lab web page.
	 * 
	 * <p>
	 * If the caller is equal to null, then you should simply pop the call
	 * stack. Otherwise, you should have the caller invoke
	 * <code>receiveReturnValue</code> to accept the value of F(n), which can be
	 * obtained from the method <code>getReturnValue</code>, and then pop the
	 * call stack.
	 * 
	 * @param callStack
	 *            the call stack
	 */
	public void returnValueToCaller(Stack<FibonacciStackFrame> callStack) {

		if (this.caller == null)
			callStack.pop();
		else {
			this.caller.receiveReturnValue(this.sum);
			callStack.pop();
		}
	}

	/**
	 * Start or resume execution of this stack frame. This method is expected to
	 * be invoked when this stack frame is on top of the call stack. When this
	 * method is invoked, this stack frame can be in one of six possible states:
	 * 
	 * <ol>
	 * <li>this stack frame is computing F(0), in which case the value 0 can be
	 * returned to the caller
	 * <li>this stack frame is computing F(1), in which case the value 1 can be
	 * returned to the caller
	 * <li>this stack frame is computing F(n) and F(n) is in the cache, in which
	 * case the value F(n) can be retrieved from the cache and returned to the
	 * caller
	 * <li>this stack frame is computing F(n - 1), in which case this stack
	 * frame should push a new stack frame onto the call stack to compute F(n -
	 * 1) passing <code>this</code> as the caller of the new stack frame, and
	 * then return from this method
	 * <li>this stack frame is computing F(n - 2), in which case this stack
	 * frame should push a new stack frame onto the call stack to compute F(n -
	 * 2) passing <code>this</code> as the caller of the new stack frame, and
	 * then return from this method
	 * <li>this stack frame is computing the sum F(n - 1) + F(n - 2), in which
	 * case this stack frame should compute the sum, put the sum in the cache,
	 * and return the value of the sum to the caller
	 * </ol>
	 * 
	 * <p>
	 * You should write an if-else-if statement that covers the 6 cases
	 * described above.
	 * </p>
	 * 
	 * @param callStack
	 *            the call stack that this frame is on top of
	 */
	public void execute(Stack<FibonacciStackFrame> callStack) {
		if (this.n == 0) {
			this.sum = BigInteger.ZERO;
			FibonacciStackFrame.cache.put(0, this.sum);
			this.returnValueToCaller(callStack);
		} else if (this.n == 1) {
			this.sum = BigInteger.ONE;
			FibonacciStackFrame.cache.put(1, this.sum);
			this.returnValueToCaller(callStack);
		} else if (FibonacciStackFrame.cache.get(n) != null) {
			this.sum = cache.get(this.n);
			this.returnValueToCaller(callStack);
		} else if (!this.fMinus1Computed) {
			callStack.push(new FibonacciStackFrame(this.n - 1, this));
			this.fMinus1 = FibonacciStackFrame.cache.get(n - 1);
		} else if (!this.fMinus2Computed) {
			callStack.push(new FibonacciStackFrame(this.n - 2, this));
			this.fMinus2 = FibonacciStackFrame.cache.get(n - 2);
		} else {
			this.sum = this.fMinus1.add(this.fMinus2);
			FibonacciStackFrame.cache.put(this.n, this.sum);
			this.returnValueToCaller(callStack);
		}
	}

	// EVERYTHING BELOW THIS LINE HAS ALREADY BEEN IMPLEMENTED FOR YOU

	/**
	 * Get the return value (F(n)) from this stack frame. The return value is
	 * just this.sum (which is equal to F(n-1) + F(n-2) when this frame has
	 * finished all of its work).
	 * 
	 * <p>
	 * If you call this method before the frame has finished all of its work
	 * then you will get the wrong value for the sum.
	 * 
	 * @return the value of F(n)
	 */
	public BigInteger getReturnValue() {
		// ALREADY COMPLETED FOR YOU
		return this.sum;
	}

	/**
	 * Get the cache of already computed Fibonacci numbers. This method is here
	 * for debugging and testing purposes.
	 * 
	 * @return the cache of already computed Fibonacci numbers
	 */
	public static Map<Integer, BigInteger> getCache() {
		return FibonacciStackFrame.cache;
	}

	/**
	 * Get the value of n. This method is here for debugging and testing
	 * purposes.
	 * 
	 * @return the value of n
	 */
	public int getN() {
		return this.n;
	}

	/**
	 * Get the creator of this call stack. This method is here for debugging and
	 * testing purposes.
	 * 
	 * @return the creator of this call stack
	 */
	public FibonacciStackFrame getCaller() {
		return this.caller;
	}

	/**
	 * Get the current value of fMinus1. This method is here for debugging and
	 * testing purposes.
	 * 
	 * @return the current value of fMinus1
	 */
	public BigInteger getfMinus1() {
		return this.fMinus1;
	}

	/**
	 * Get the current value of fMinus2. This method is here for debugging and
	 * testing purposes.
	 * 
	 * @return the current value of fMinus2
	 */
	public BigInteger getfMinus2() {
		return this.fMinus2;
	}

	/**
	 * Get the current value of fMinus1Computed. This method is here for
	 * debugging and testing purposes.
	 * 
	 * @return the current value of fMinus1Computed
	 */
	public boolean getfMinus1Computed() {
		return this.fMinus1Computed;
	}

	/**
	 * Get the current value of fMinus2Computed. This method is here for
	 * debugging and testing purposes.
	 * 
	 * @return the current value of fMinus2Computed
	 */
	public boolean getfMinus2Computed() {
		return this.fMinus2Computed;
	}

	/**
	 * Set the value of this.sum. This method is here for debugging and testing
	 * purposes.
	 * 
	 * @param sum
	 *            the new value for this.sum
	 */
	public void setSum(BigInteger sum) {
		this.sum = sum;
	}
}