local userId = ARGV[1]
local friendId = ARGV[2]

if userId == friendId then
    return {err = "Cannot add yourself as a friend"}
end

if redis.call("SISMEMBER", "friends:" .. userId, friendId) == 1 then
    return {err = "Friendship already exists"}
end

redis.call("SADD", "friends:" .. userId, friendId)
redis.call("SADD", "friends:" .. friendId, userId)

return "OK"
