Write a function&nbsp;<code>argumentsLength</code> that returns the count of arguments passed to it.

<p>&nbsp;</p> 
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> args = [5]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
argumentsLength(5); // 1

One value was passed to the function so it should return 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> args = [{}, null, "3"]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
argumentsLength({}, null, "3"); // 3

Three values were passed to the function so it should return 3.
</pre>

<p>&nbsp;</p> 
<p><strong>Constraints:</strong></p>

<ul> 
 <li><code>args</code>&nbsp;is a valid JSON array</li> 
 <li><code>0 &lt;= args.length &lt;= 100</code></li> 
</ul>

<div><li>👍 334</li><li>👎 166</li></div>