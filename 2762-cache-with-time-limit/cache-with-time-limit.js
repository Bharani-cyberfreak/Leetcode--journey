var TimeLimitedCache = function() {
    this.cache = new Map();
};

TimeLimitedCache.prototype.set = function(key, value, duration) {
    const now = Date.now();

    if (this.cache.has(key)) {
        const item = this.cache.get(key);

        clearTimeout(item.timer);

        this.cache.set(key, {
            value: value,
            expiry: now + duration,
            timer: setTimeout(() => {
                if (this.cache.has(key) &&
                    this.cache.get(key).expiry <= Date.now()) {
                    this.cache.delete(key);
                }
            }, duration)
        });

        return true;
    }

    this.cache.set(key, {
        value: value,
        expiry: now + duration,
        timer: setTimeout(() => {
            if (this.cache.has(key) &&
                this.cache.get(key).expiry <= Date.now()) {
                this.cache.delete(key);
            }
        }, duration)
    });

    return false;
};

TimeLimitedCache.prototype.get = function(key) {
    if (!this.cache.has(key)) return -1;

    const item = this.cache.get(key);

    if (item.expiry <= Date.now()) {
        clearTimeout(item.timer);
        this.cache.delete(key);
        return -1;
    }

    return item.value;
};

TimeLimitedCache.prototype.count = function() {
    const now = Date.now();
    let count = 0;

    for (const [key, item] of this.cache) {
        if (item.expiry > now) {
            count++;
        } else {
            clearTimeout(item.timer);
            this.cache.delete(key);
        }
    }

    return count;
};