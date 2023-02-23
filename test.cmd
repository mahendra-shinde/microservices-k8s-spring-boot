@Echo off
set PARA1=%1
echo "Hello %PARA1%"

IF "%PARA1%" == "Hello" (
	echo "Why just Hello ?"
) ELSE (
	echo "That's the way !!"
)